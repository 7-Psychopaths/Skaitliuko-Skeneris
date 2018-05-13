#include <com_a7psychopaths_skaitliukoskeneris_Recognition.h>

class ContourWithData {
    public:
    	vector<Point> ptContour;           // contour
    	Rect boundingRect;                      // bounding rect for contour
    	float fltArea;

    	bool checkIfContourIsValid() {
    		if (fltArea < MIN_CONTOUR_AREA || fltArea > MAX_CONTOUR_AREA || boundingRect.width > 60 || boundingRect.width < 14
    		 || boundingRect.height > 85 || boundingRect.height < 40) return false;
    		return true;
    	}

    	static bool sortByBoundingRectXPosition(const ContourWithData& cwdLeft, const ContourWithData& cwdRight) {      // sort from left to right
    		return(cwdLeft.boundingRect.x < cwdRight.boundingRect.x);
    	}

    };

JNIEXPORT jint JNICALL Java_com_a7psychopaths_skaitliukoskeneris_Recognition_getDigits
    (JNIEnv *env, jclass, jlong addrRgba, jstring path){

    Mat& mRgb = *(Mat*)addrRgba;
    jint retVal;

    const char *cstr = env->GetStringUTFChars(path, NULL);
    string pathToStorage = string(cstr);

    vector<ContourWithData> allContoursWithData;
	vector<ContourWithData> validContoursWithData;

	Mat matClassificationInts;

    FileStorage fsClassifications(pathToStorage+"/classifications.xml", FileStorage::READ);
    fsClassifications["classifications"] >> matClassificationInts;

	fsClassifications.release();

    Mat matTrainingImagesAsFlattenedFloats;

    FileStorage fsTrainingImages(pathToStorage+"/images.xml", FileStorage::READ);

    fsTrainingImages["images"] >> matTrainingImagesAsFlattenedFloats;
    fsTrainingImages.release();

    KNearest kNearest = KNearest();					// instantiate the KNN object
    kNearest.train(matTrainingImagesAsFlattenedFloats, matClassificationInts);

    Mat matGrayscale;
    Mat matBlurred;
    	Mat matThresh;
    	Mat matThreshCopy;

    	Mat imgTopHat;
    	Mat imgBlackHat;
    	Mat imgGrayscalePlusTopHat;
    	Mat imgGrayscalePlusTopHatMinusBlackHat;

    	Mat structuringElement = getStructuringElement(CV_SHAPE_RECT, Size(3, 3));

    	cvtColor(mRgb, matGrayscale, CV_BGR2GRAY);         // convert to grayscale


        	morphologyEx(matGrayscale, imgTopHat, CV_MOP_TOPHAT, structuringElement);
        	morphologyEx(matGrayscale, imgBlackHat, CV_MOP_BLACKHAT, structuringElement);

        	imgGrayscalePlusTopHat = matGrayscale + imgTopHat;
        	imgGrayscalePlusTopHatMinusBlackHat = imgGrayscalePlusTopHat - imgBlackHat;
        																		// blur
        	GaussianBlur(imgGrayscalePlusTopHatMinusBlackHat,
        		matBlurred,
        		Size(5, 5),            // smoothing window width and height in pixels
        		0);                        // sigma value = blur

        								   // filter image from grayscale to black and white
        	adaptiveThreshold(matBlurred,
        		matThresh,
        		255,                                  // make pixels that pass the threshold full white
        		ADAPTIVE_THRESH_GAUSSIAN_C,       // use gaussian rather than mean, seems to give better results
        		THRESH_BINARY_INV,                // invert so foreground will be white, background will be black
        		11,                                   // size of a pixel neighborhood used to calculate threshold value
        		2);                                   // constant subtracted from the mean or weighted mean

        	matThreshCopy = matThresh.clone();              // make a copy of the thresh image, this in necessary b/c findContours modifies the image

    vector<vector<Point> > ptContours;        // declare a vector for the contours
	vector<Vec4i> v4iHierarchy;

	findContours(matThreshCopy,             // input image, make sure to use a copy since the function will modify this image in the course of finding contours
    		ptContours,                             // output contours
    		v4iHierarchy,                           // output hierarchy
    		RETR_EXTERNAL,                      // retrieve the outermost contours only
    		CHAIN_APPROX_SIMPLE);

    for (int i = 0; i < ptContours.size(); i++) {               // for each contour
        		ContourWithData contourWithData;                                                    // instantiate a contour with data object
        		contourWithData.ptContour = ptContours[i];                                          // assign contour to contour with data
        		contourWithData.boundingRect = boundingRect(contourWithData.ptContour);         // get the bounding rect
        		contourWithData.fltArea = contourArea(contourWithData.ptContour);               // calculate the contour area
        		allContoursWithData.push_back(contourWithData);                                     // add contour with data object to list of all contours with data
        	}

    for (int i = 0; i < allContoursWithData.size(); i++) {                      // for all contours
    			if (allContoursWithData[i].checkIfContourIsValid()) {                   // check if valid
    				validContoursWithData.push_back(allContoursWithData[i]);            // if so, append to valid contour list
    			}
    	}
    	// sort contours from left to right
    	sort(validContoursWithData.begin(), validContoursWithData.end(), ContourWithData::sortByBoundingRectXPosition);

    	string strFinalString;

    	for (int i = 0; i < validContoursWithData.size(); i++) {            // for each contour

        																		// draw a green rect around the current char
        		rectangle(mRgb,                            // draw rectangle on original image
        			validContoursWithData[i].boundingRect,        // rect to draw
        			Scalar(0, 255, 0),                        // green
        			2);                                           // thickness

        		Mat matROI = matThresh(validContoursWithData[i].boundingRect);          // get ROI image of bounding rect

        		Mat matROIResized;
        		resize(matROI, matROIResized, Size(RESIZED_IMAGE_WIDTH, RESIZED_IMAGE_HEIGHT));     // resize image, this will be more consistent for recognition and storage

        		Mat matROIFloat;
        		matROIResized.convertTo(matROIFloat, CV_32FC1);             // convert Mat to float, necessary for call to find_nearest

        		Mat matROIFlattenedFloat = matROIFloat.reshape(1, 1);

        		Mat matCurrentChar(0, 0, CV_32F);

        		float fltCurrentChar = kNearest.find_nearest(matROIFloat.reshape(1, 1),		// flattened resized ROI
                													 1);							// K

                strFinalString = strFinalString + char(int(fltCurrentChar));
        	}
    int number;

    number = atoi(strFinalString.c_str());

    retVal = (jint) number;

    return retVal;
    }



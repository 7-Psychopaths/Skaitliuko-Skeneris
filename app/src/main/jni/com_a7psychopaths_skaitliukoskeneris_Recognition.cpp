#include <com_a7psychopaths_skaitliukoskeneris_Recognition.h>

JNIEXPORT jint JNICALL Java_com_a7psychopaths_skaitliukoskeneris_Recognition_getDigits
    (JNIEnv *, jclass, jlong addrRgba){

    Mat& mRgb = *(Mat*)addrRgba;

    int conv = 50;
    jint retVal;

    retVal = (jint) conv;

    vector<ContourWithData> allContoursWithData;
	vector<ContourWithData> validContoursWithData;


	Mat matClassificationInts;

	FileStorage fsClassifications("app//src//main//assets//classifications.xml", FileStorage::READ);

	if (fsClassifications.isOpened() == false) {
	    conv = 25;
	    retVal = (jint) conv;
		return retVal;
	}

    return retVal;
    }

    class ContourWithData {
    public:
    	vector<Point> ptContour;           // contour
    	Rect boundingRect;                      // bounding rect for contour
    	float fltArea;

    	bool checkIfContourIsValid() {
    		if (fltArea < MIN_CONTOUR_AREA || fltArea > MAX_CONTOUR_AREA) return false;
    		return true;
    	}

    	static bool sortByBoundingRectXPosition(const ContourWithData& cwdLeft, const ContourWithData& cwdRight) {      // sort from left to right
    		return(cwdLeft.boundingRect.x < cwdRight.boundingRect.x);
    	}

    };



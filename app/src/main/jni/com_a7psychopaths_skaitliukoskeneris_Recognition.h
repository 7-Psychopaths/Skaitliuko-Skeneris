/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdio.h>
#include <opencv2/opencv.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/ml/ml.hpp>
#include<iostream>
#include<sstream>

using namespace std;
using namespace cv;

const int MIN_CONTOUR_AREA = 200;
const int MAX_CONTOUR_AREA = 5000;

const int RESIZED_IMAGE_WIDTH = 20;
const int RESIZED_IMAGE_HEIGHT = 30;
/* Header for class com_a7psychopaths_skaitliukoskeneris_Recognition */

#ifndef _Included_com_a7psychopaths_skaitliukoskeneris_Recognition
#define _Included_com_a7psychopaths_skaitliukoskeneris_Recognition
#ifdef __cplusplus
extern "C" {
#endif

class ContourWithData;

JNIEXPORT jint JNICALL Java_com_a7psychopaths_skaitliukoskeneris_Recognition_getDigits
    (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif

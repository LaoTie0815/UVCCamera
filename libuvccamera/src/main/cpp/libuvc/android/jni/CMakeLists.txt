# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# 设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../../jniLibs/${CMAKE_ANDROID_ARCH_ABI})

set(CMAKE_VERBOSE_MAKEFILE on)

#add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/../../../libjpeg-turbo-1.5.0/android)
#add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/../../../libusb/android/jni)

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../include/libuvc)

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../libusb)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../libusb/libusb)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../libusb/libusb/os)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../libusb/android)

#add_library(jpeg-turbo1500 SHARED IMPORTED )
#set_target_properties(
#        # Specifies the target library.
#        jpeg-turbo1500
#        # Specifies the parameter you want to define.
#        PROPERTIES IMPORTED_LOCATION
#        # Provides the path to the library you want to import.
#        "${CMAKE_CURRENT_SOURCE_DIR}/../../../libjpeg-turbo-1.5.0/jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libjpeg-turbo1500.so")
#
#add_library(usb100 SHARED IMPORTED )
#set_target_properties(
#        # Specifies the target library.
#        usb100
#        # Specifies the parameter you want to define.
#        PROPERTIES IMPORTED_LOCATION
#        # Provides the path to the library you want to import.
#        "${CMAKE_CURRENT_SOURCE_DIR}/../../../libusb/jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libusb100.so")


add_library( # Sets the name of the library.
        uvc
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ../../src/ctrl.c
        ../../src/device.c
        ../../src/diag.c
        ../../src/frame.c
        ../../src/frame-mjpeg.c
        ../../src/init.c
        ../../src/stream.c
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
find_library(log-lib
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries(uvc
        ${log-lib})
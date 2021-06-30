# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
PROJECT(LaoTie_UvcCamera)

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)

# 设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cppLibs/${CMAKE_ANDROID_ARCH_ABI})

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp)

#libjpeg-turbo-1.5.0
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libjpeg-turbo-1.5.0)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libjpeg-turbo-1.5.0/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libjpeg-turbo-1.5.0/simd)
#libjpeg-turbo-1.5.0

#libusb
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libusb)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libusb/libusb)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libusb/libusb/os)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libusb/android)
#libusb

#libuvc
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libuvc)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libuvc/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/libuvc/include/libuvc)
#libuvc

#libUVCCamera
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/UVCCamera)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/rapidjson/include/rapidjson)
#libUVCCamera

add_definitions(-D ACCESS_RAW_DESCRIPTORS)
add_definitions(-D ANDROID_NDK)
add_definitions(-D LOG_NDEBUG)

if (${CMAKE_ANDROID_ARCH_ABI} MATCHES "arm64-v8a") #针对不同 ABI 设置不同的编译代码 或 if(${ANDROID_ABI} STREQUAL "areambi")
    enable_language(ASM)
    SET(JSIMD_ARM src/main/cpp/libjpeg-turbo-1.5.0/simd/jsimd_arm64.c)
    SET(JSIMD_ARM_NEON src/main/cpp/libjpeg-turbo-1.5.0/simd/jsimd_arm64_neon.S)
    add_definitions(-D SIZEOF_SIZE_T=4)
elseif (${CMAKE_ANDROID_ARCH_ABI} MATCHES "armeabi-v7a")
    enable_language(ASM)
    SET(JSIMD_ARM src/main/cpp/libjpeg-turbo-1.5.0/simd/jsimd_arm.c)
    SET(JSIMD_ARM_NEON src/main/cpp/libjpeg-turbo-1.5.0/simd/jsimd_arm_neon.S)
    add_definitions(-D SIZEOF_SIZE_T=8)
endif ()

aux_source_directory(src/main/cpp DIR_SRC_ROOT)

add_library( # Sets the name of the library.
        LaoTie_UvcCamera
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).

        #libjpeg-turbo-1.5.0
        src/main/cpp/libjpeg-turbo-1.5.0/jcapimin.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcapistd.c
        src/main/cpp/libjpeg-turbo-1.5.0/jccoefct.c
        src/main/cpp/libjpeg-turbo-1.5.0/jccolor.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcdctmgr.c
        src/main/cpp/libjpeg-turbo-1.5.0/jchuff.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcinit.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcmainct.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcmarker.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcmaster.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcomapi.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcparam.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcphuff.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcprepct.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcsample.c
        src/main/cpp/libjpeg-turbo-1.5.0/jctrans.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdapimin.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdapistd.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdatadst.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdatasrc.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdcoefct.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdcolor.c
        src/main/cpp/libjpeg-turbo-1.5.0/jddctmgr.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdhuff.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdinput.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdmainct.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdmarker.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdmaster.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdmerge.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdphuff.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdpostct.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdsample.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdtrans.c
        src/main/cpp/libjpeg-turbo-1.5.0/jerror.c
        src/main/cpp/libjpeg-turbo-1.5.0/jfdctflt.c
        src/main/cpp/libjpeg-turbo-1.5.0/jfdctfst.c
        src/main/cpp/libjpeg-turbo-1.5.0/jfdctint.c
        src/main/cpp/libjpeg-turbo-1.5.0/jidctflt.c
        src/main/cpp/libjpeg-turbo-1.5.0/jidctfst.c
        src/main/cpp/libjpeg-turbo-1.5.0/jidctint.c
        src/main/cpp/libjpeg-turbo-1.5.0/jidctred.c
        src/main/cpp/libjpeg-turbo-1.5.0/jquant1.c
        src/main/cpp/libjpeg-turbo-1.5.0/jquant2.c
        src/main/cpp/libjpeg-turbo-1.5.0/jutils.c
        src/main/cpp/libjpeg-turbo-1.5.0/jmemmgr.c
        src/main/cpp/libjpeg-turbo-1.5.0/jmemnobs.c
        src/main/cpp/libjpeg-turbo-1.5.0/jaricom.c
        src/main/cpp/libjpeg-turbo-1.5.0/jcarith.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdarith.c
        src/main/cpp/libjpeg-turbo-1.5.0/turbojpeg.c
        src/main/cpp/libjpeg-turbo-1.5.0/transupp.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdatadst-tj.c
        src/main/cpp/libjpeg-turbo-1.5.0/jdatasrc-tj.c
        ${JSIMD_ARM}
        ${JSIMD_ARM_NEON}
        #libjpeg-turbo-1.5.0

        #libusb
        src/main/cpp/libusb/libusb/core.c
        src/main/cpp/libusb/libusb/descriptor.c
        src/main/cpp/libusb/libusb/hotplug.c
        src/main/cpp/libusb/libusb/io.c
        src/main/cpp/libusb/libusb/sync.c
        src/main/cpp/libusb/libusb/strerror.c
        src/main/cpp/libusb/libusb/os/android_usbfs.c
        src/main/cpp/libusb/libusb/os/poll_posix.c
        src/main/cpp/libusb/libusb/os/threads_posix.c
        src/main/cpp/libusb/libusb/os/android_netlink.c
        #libusb

        #libuvc
        src/main/cpp/libuvc/src/ctrl.c
        src/main/cpp/libuvc/src/device.c
        src/main/cpp/libuvc/src/diag.c
        src/main/cpp/libuvc/src/frame.c
        src/main/cpp/libuvc/src/frame-mjpeg.c
        src/main/cpp/libuvc/src/init.c
        src/main/cpp/libuvc/src/stream.c
        #libuvc

        #libUVCCamera
        src/main/cpp/UVCCamera/UVCCamera.cpp
        src/main/cpp/UVCCamera/UVCPreview.cpp
        src/main/cpp/UVCCamera/UVCButtonCallback.cpp
        src/main/cpp/UVCCamera/UVCStatusCallback.cpp
        src/main/cpp/UVCCamera/Parameters.cpp
        #libUVCCamera

        src/main/cpp/localdefines.h
        src/main/cpp/utilbase.cpp
        src/main/cpp/utilbase.h
        src/main/cpp/mid_exceptions.cpp
        src/main/cpp/mid_exceptions.h
        src/main/cpp/jni.cpp

#        ${DIR_SRC_ROOT}
#        ${DIR_SRC_BR}
#        ${DIR_EIGEN}
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
target_link_libraries(LaoTie_UvcCamera
        -ldl
        -landroid
        ${log-lib})
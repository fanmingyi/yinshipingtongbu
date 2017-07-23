# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.c )


set(distribution_DIR   ${CMAKE_SOURCE_DIR}/libs/)



find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

find_library( # Sets the name of the path variable.
              android-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              android )




#libavcodec-56.so
add_library(libavcodec-56-lib SHARED IMPORTED)
set_target_properties(libavcodec-56-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libavcodec-56.so)

#libavdevice-56.so
add_library(libavdevice-56-lib SHARED IMPORTED)
set_target_properties(libavdevice-56-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libavdevice-56.so)

#libavfilter-5.so
add_library(libavfilter-5-lib SHARED IMPORTED)
set_target_properties(libavfilter-5-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libavfilter-5.so)

#libavformat-56.so
add_library(libavformat-56-lib SHARED IMPORTED)
set_target_properties(libavformat-56-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libavformat-56.so)

#libavutil-54.so
add_library(libavutil-54-lib SHARED IMPORTED)
set_target_properties(libavutil-54-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libavutil-54.so)


#libpostproc-53.so
add_library(libpostproc-53-lib SHARED IMPORTED)
set_target_properties(libpostproc-53-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libpostproc-53.so)


#libswresample-1.so
add_library(libswresample-1-lib SHARED IMPORTED)
set_target_properties(libswresample-1-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libswresample-1.so)


#libswscale-3.so
add_library(libswscale-3-lib SHARED IMPORTED)
set_target_properties(libswscale-3-lib  PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libswscale-3.so)

#libyuv.so
add_library(libyuv-lib SHARED IMPORTED)
set_target_properties(
libyuv-lib PROPERTIES IMPORTED_LOCATION
${distribution_DIR}/${ANDROID_ABI}/libyuv.so
)
include_directories(src/main/cpp/include/)
target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib}
                       ${android-lib}
                       libavcodec-56-lib
                       libavdevice-56-lib
                       libavfilter-5-lib
                       libavformat-56-lib
                       libavutil-54-lib
                       libpostproc-53-lib
                       libswresample-1-lib
                       libswscale-3-lib
                        libyuv-lib
                        )
-keepattributes Signature
-keepattributes *Annotation*

# gson
-keepattributes EnclosingMethod

-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# OkHttp
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

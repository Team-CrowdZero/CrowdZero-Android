import com.gdg.build_logic.configureCoroutineAndroid
import com.gdg.build_logic.configureHiltAndroid
import com.gdg.build_logic.configureKotlinAndroid
import com.gdg.build_logic.configureSerializationAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()
configureSerializationAndroid()

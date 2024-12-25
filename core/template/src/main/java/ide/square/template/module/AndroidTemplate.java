package ide.square.template.module;

import android.content.Context;
import ide.square.template.BaseTemplate;
import ide.square.template.TemplateFile;
import java.io.IOException;

public class AndroidTemplate extends BaseTemplate {
    private String projectName;
    private String packageName;

    public AndroidTemplate(String projectName, String packageName, Context context) {
        super("AndroidProject", context);
        
        this.projectName = projectName;
        this.packageName = packageName;
        
        createTemplateFiles();
    }

    @Override
    public void createTemplateFiles() {
        addFile(new TemplateFile("app/build.gradle.kts",
            "plugins {\n" +
            "    alias(libs.plugins.android.application)\n" +
            "}\n" +
            "\n" +
            "android {\n" +
            "    namespace = \"" + packageName + "\"\n" +
            "    compileSdk = 34\n" +
            "    \n" +
            "    defaultConfig {\n" +
            "        applicationId = \"" + packageName + "\"\n" +
            "        \n" +
            "        targetSdk = 34\n" +
            "        minSdk = 21\n" +
            "        \n" +
            "        versionCode = 1\n" +
            "        versionName = \"1.0\"\n" +
            "    }\n" +
            "    \n" +
            "    buildTypes {\n" +
            "        release {\n" +
            "            isMinifyEnabled = false\n" +
            "            proguardFiles(getDefaultProguardFile(\"proguard-android-optimize.txt\"), \"proguard-rules.pro\")\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    compileOptions {\n" +
            "        sourceCompatibility = JavaVersion.VERSION_17\n" +
            "        targetCompatibility = JavaVersion.VERSION_17\n" +
            "    }\n" +
            "    \n" +
            "    buildFeatures {\n" +
            "        viewBinding = true\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "dependencies {\n" +
            "    implementation(libs.androidx.appcompat)\n" +
            "    implementation(libs.google.material)\n" +
            "}"
        ));

        addFile(new TemplateFile("settings.gradle.kts",
            "pluginManagement {\n" +
            "    repositories {\n" +
            "        google {\n" +
            "            content {\n" +
            "                includeGroupByRegex(\"com\\.android.*\")\n" +
            "                includeGroupByRegex(\"com\\.google.*\")\n" +
            "                includeGroupByRegex(\"androidx.*\")\n" +
            "            }\n" +
            "        }\n" +
            "        mavenCentral()\n" +
            "        gradlePluginPortal()\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "dependencyResolutionManagement {\n" +
            "    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)\n" +
            "    \n" +
            "    repositories {\n" +
            "        google()\n" +
            "        mavenCentral()\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "rootProject.name = \"" + projectName + "\"\n" +
            "include(\":app\")"
        ));

        addFile(new TemplateFile("app/src/main/java/" + packageName.replace('.', '/') + "/MainActivity.java",
            "package " + packageName + ";\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "import androidx.appcompat.app.AppCompatActivity;\n" +
            "import " + packageName + ".databinding.ActivityMainBinding;\n"+
            "\n" +
            "public class MainActivity extends AppCompatActivity {\n" +
            "    public ActivityMainBinding binding;\n" +
            "    \n" +
            "    @Override\n" +
            "    protected void onCreate(Bundle savedInstanceState) {\n" +
            "        super.onCreate(savedInstanceState);\n" +
            "        \n" +
            "        binding = ActivityMainBinding.inflate(getLayoutInflater());\n" +
            "        \n" +
            "        setContentView(binding.getRoot());\n" +
            "    }\n" +
            "    \n" +
            "    @Override\n" +
            "    public void onDestroy() {\n" +
            "        super.onDestroy();\n" +
            "        \n" +
            "        binding = null;\n" +
            "    }\n" +
            "}"
        ));
        
        try {
            addFileFromApkAssets("app/proguard-rules.pro", "template/base/app/proguard-rules.pro");
            addFileFromApkAssets("gradle.properties", "template/base/gradle.properties");
            addFileFromApkAssets("build.gradle.kts", "template/base/build.gradle.kts");
            addFileFromApkAssets("gradlew", "template/base/gradlew");
            addFileFromApkAssets("gradlew.bat", "template/base/gradlew.bat");
            addFileFromApkAssets("gradle/libs.versions.toml", "template/base/gradle/libs.versions.toml");
            addFileFromApkAssets("gradle/wrapper/gradle-wrapper.properties", "template/base/gradle/wrapper/gradle-wrapper.properties");
            addFileFromApkAssets("gradle/wrapper/gradle-wrapper.jar", "template/base/gradle/wrapper/gradle-wrapper.jar");
            addFileFromApkAssets(".gitignore", "template/base/gitignore");
            addFileFromApkAssets("app/.gitignore", "template/base/app/gitignore");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
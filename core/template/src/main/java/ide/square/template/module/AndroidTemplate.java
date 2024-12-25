package ide.square.template.module;

import ide.square.template.BaseTemplate;
import ide.square.template.TemplateFile;

public class AndroidTemplate extends BaseTemplate {
    private String projectName;
    private String packageName;

    public AndroidTemplate(String projectName, String packageName) {
        super("AndroidProject");
        
        this.projectName = projectName;
        this.packageName = packageName;
        
        createTemplateFiles();
    }

    @Override
    public void createTemplateFiles() {
        addFile(new TemplateFile("build.gradle.kts",
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
            "        \n" +
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
            "rootProject.name = \"Square IDE\"\n" +
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
    }
}
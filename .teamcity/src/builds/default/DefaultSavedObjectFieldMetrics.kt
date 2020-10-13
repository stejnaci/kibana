package builds.default

import addTestArtifacts
import failedTestReporter
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import junit

object DefaultSavedObjectFieldMetrics : BuildType({
  id("DefaultSavedObjectFieldMetrics")
  name = "Default Saved Object Field Metrics"
  paused = true

  params {
    param("env.KBN_NP_PLUGINS_BUILT", "true")
  }

  steps {
    script {
      name = "Default Saved Object Field Metrics"
      scriptContent =
        """
          #!/bin/bash
          ./.ci/teamcity/default/saved_object_field_metrics.sh
        """.trimIndent()
    }

    failedTestReporter()
  }

  features {
    junit()
  }

  dependencies {
    defaultBuild()
  }

  addTestArtifacts()
})

/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.fixtures.kotlin.dsl

import org.gradle.integtests.fixtures.RepoScriptBlockUtil
import org.gradle.integtests.fixtures.executer.GradleExecuter
import org.gradle.test.fixtures.file.TestFile

class KotlinEapRepoUtil {

    static void withKotlinEapRepository(TestFile baseDir, GradleExecuter executer) {
        def eapRepoInit = baseDir.file("kotlin-eap-repo.init.gradle") << """
            allprojects {
                repositories {
                    ${RepoScriptBlockUtil.kotlinEapRepositoryDefinition()}
                }
            }
        """
        executer.beforeExecute {
            it.withArguments("-I", eapRepoInit.canonicalPath)
        }
    }

    static File createKotlinEapInitScript() {
        File initScript = File.createTempFile("kotlin-eap-repo", ".gradle")
        initScript.deleteOnExit()
        initScript << kotlinEapRepoInitScript()
        return initScript
    }

    private static String kotlinEapRepoInitScript() {
        return """
            allprojects {
                repositories {
                    ${RepoScriptBlockUtil.kotlinEapRepositoryDefinition()}
                }
            }
        """
    }
}
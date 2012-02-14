/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unifei.rmaalmeida.toolchainSDCC;

import com.microchip.mplab.nbide.embedded.spi.FilenameSuffixProvider;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectConfiguration;

public class SDCCOutputFilenameSuffixProvider implements FilenameSuffixProvider {

    public String getFilenameSuffix(Project project, ProjectConfiguration projectConf, boolean isDebug) {
        if (!isDebug) {
            return "hex"; // NOI18N
        }
        return "cof";
    }

}


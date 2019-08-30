/*
    SDCC Toolchain allows MPLABX to compile with SDCC+GPUtils for pic 16/18
    Copyright (C) 2011 RMA de Almeida, 2015 ZJ Laczik and 2016 P Hayes.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.laczik.toolchainSDCC;

import com.microchip.mplab.nbide.embedded.spi.IncludeProvider;
import com.microchip.mplab.nbide.embedded.makeproject.api.configurations.MakeConfiguration;
import com.microchip.mplab.nbide.toolchainCommon.properties.CommonLanguageToolchainPropertiesUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectConfiguration;

public class SDCCSystemIncludeProvider implements IncludeProvider {

    private final CommonLanguageToolchainPropertiesUtils utils = new CommonLanguageToolchainPropertiesUtils();

    public SDCCSystemIncludeProvider() {
    }

    @Override
    public List<String> getIncludes(Project project, ProjectConfiguration projectConf, String itemPath) {
        MakeConfiguration makeConf = (MakeConfiguration)projectConf;
        String ProcessorFamily = SDCCChipDependentProperties.getProcessorFamilyForCompiler(makeConf);
       
        final List<String> ret = new ArrayList<>();
        final String pathToBin = utils.pathToBin(projectConf);
        ret.add(pathToBin + File.separator + ".." + File.separator + "include" + File.separator + ProcessorFamily);
        ret.add(pathToBin + File.separator + ".." + File.separator + "include");
        ret.add(pathToBin + File.separator + ".." + File.separator + "non-free" + File.separator + "include" + File.separator  + ProcessorFamily);
        ret.add(pathToBin + File.separator + ".." + File.separator + "non-free" + File.separator + "include");
        return ret;
    }
}

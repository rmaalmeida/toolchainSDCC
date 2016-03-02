/*
    SDCC Toolchain allows MPLABX to compile with SDCC+GPUtils for pic 16/18
    Copyright (C) 2011 RMA de Almeida and 2015 ZJ Laczik

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

import com.microchip.mplab.nbide.embedded.spi.DefineProvider;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectConfiguration;
import com.microchip.mplab.nbide.embedded.makeproject.api.configurations.MakeConfiguration;
import com.microchip.mplab.nbide.toolchainCommon.LTUtils;
import java.util.Arrays;

public class SDCCSystemDefineProvider implements DefineProvider {

    private static final String[] PREDEFINED_MACROS = {
        "__asm nop __endasm",
        "__asm crwdt __endasm",
        "__asm sleep __endasm",
        "__asm reset __endasm"
    };
    
    @Override
    public List<String> getDefines(Project project, ProjectConfiguration projectConf, String itemPath) {
        List<String> res = Arrays.asList(PREDEFINED_MACROS);
        res = new ArrayList<>(res);

        MakeConfiguration makeConf = (MakeConfiguration)projectConf;
        
        // These defines can be found by running the compiler in verbose mode.
        res.add("__SDCC_PROCESSOR=" + SDCCChipDependentProperties.getProcessorShortNameForCompiler(makeConf));  // __SDCC_PROCESSOR=16f628a
        res.add("__SDCC_" + SDCCChipDependentProperties.getProcessorNameForCompiler(makeConf));                 // __SDCC_PIC16F628A
//        res.add("__SDCC_USE_NON_FREE");                                                                         // option!!
        res.add("__SDCC=" + getCompilerVersion(makeConf));                                                      // __SDCC=3_5_0
        res.add("SDCC=" + getCompilerVersion2(makeConf));                                                       // SDCC=350
//        res.add("__SDCC_REVISION=" + getCompilerRevision());                                                    // __SDCC_REVISION=9253
        res.add("__SDCC_" + SDCCChipDependentProperties.getProcessorFamilyForCompiler(makeConf));               // __SDCC_pic14
//        res.add("__STDC_NO_COMPLEX__");                                                                         // option??
//        res.add("__STDC_NO_THREADS__");                                                                         // option??
//        res.add("__STDC_NO_ATOMICS__");                                                                         // option??
//        res.add("__STDC_NO_VLA__");                                                                             // option??
        return res;
    }
    
    public static String getCompilerVersion(MakeConfiguration makeConf) {
        String ver = LTUtils.getVersion(makeConf);
        return ver.replace(".", "_");
    }

    public static String getCompilerVersion2(MakeConfiguration makeConf) {
        String ver = LTUtils.getVersion(makeConf);
        return ver.replace(".", "");
    }
    
//    public static String getCompilerRevision() {
//        return "9253";
//    }
}

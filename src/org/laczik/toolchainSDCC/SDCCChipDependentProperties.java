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

import com.microchip.mplab.nbide.embedded.makeproject.api.configurations.MakeConfiguration;

public class SDCCChipDependentProperties {
    
    private static String getDeviceName(MakeConfiguration makeConf){
        return makeConf.getDevice().getName();    
    }
            
    public static String getProcessorNameForCompiler(MakeConfiguration makeConf){
        return getDeviceName(makeConf);
    }
    
    public static String getProcessorShortNameForCompiler(MakeConfiguration makeConf) {
        String shortname = getDeviceName(makeConf).substring(3);
        return shortname.toLowerCase();
    }

    public static String getProcessorFamilyForCompiler(MakeConfiguration makeConf) {
        String deviceName = getDeviceName(makeConf);
        if (deviceName.startsWith("PIC18"))
            return "pic16";
        else if (deviceName.startsWith("PIC16"))
            return "pic14";
        else if (deviceName.startsWith("PIC12"))
            return "pic14";
        else if (deviceName.startsWith("PIC10"))
            return "pic14";
        return "none";
    }
}

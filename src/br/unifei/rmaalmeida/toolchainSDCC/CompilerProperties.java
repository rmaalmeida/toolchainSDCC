/*
    SDCC Toolchain allows MPLABX to compile with SDCC+GPUtils for pic 16/18
    Copyright (C) 2011  Rodrigo Maximiano Antunes de Almeida

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
package br.unifei.rmaalmeida.toolchainSDCC;

import com.microchip.mplab.nbide.embedded.makeproject.api.configurations.MakeConfiguration;
import com.microchip.mplab.nbide.embedded.makeproject.api.configurations.MakeConfigurationBook;
import java.util.Properties;

/**
 *
 * @author rmaalmeida
 */
public class CompilerProperties {
    public CompilerProperties(MakeConfigurationBook projectDescriptor,
            MakeConfiguration conf,
            Properties commandLineProperties) {
        commandLineProperties.put("PROCESSOR_NAME", getProcessorNameForCompiler(conf.getDevice().getValue()));
        commandLineProperties.put("PROCESSOR_FAMILY", getProcessorFamilyForCompiler(conf.getDevice().getValue()));
}

    /**
     * See return
     * @param deviceName
     * @return value of processor to be fed to compiler.
     */
    public static String getProcessorNameForCompiler(String deviceName) {
        String lower = deviceName.toLowerCase();
        return lower.substring(3, deviceName.length());
    }

    public static String getProcessorFamilyForCompiler(String deviceName) {
        String res = null;
        res = deviceName.substring(3, 5);
        if (res.startsWith("18"))
            return "pic16";
        else if (res.startsWith("16"))
            return "pic14";
        return "none";
    }
}

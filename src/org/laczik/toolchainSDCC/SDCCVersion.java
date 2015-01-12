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
import com.microchip.crownking.opt.Version;

import java.util.ArrayList;

/**
 *
 * @author MPLABX_team
 */
public class SDCCVersion extends Version
{
    private String extensions = "";
    private ArrayList<Integer> elements ;

    public SDCCVersion(String s)
    {
        super();
        String[] tokens = s.split("\\D");
        for (int n = 0; n < tokens.length; ++n)
        {
            try
            {
                elements.add(Integer.parseInt(tokens[n]));
            }
            catch(NumberFormatException ex)
            {
                extensions = tokens[n];
            }
        }
    }
    
    public int compareTo(SDCCVersion another)
    {
        for (int n = 0;
             (n < this.elements.size()) && (n < another.elements.size());
             ++n)
            {
           
                if (this.elements.get(n) < another.elements.get(n))
                    return -1;
                if (this.elements.get(n) > another.elements.get(n))
                    return 1;
            }

            if (this.elements.size() < another.elements.size())
                return -1;
            if (this.elements.size() > another.elements.size())
                return 1;

            return this.extensions.compareTo(another.extensions);
    }
}

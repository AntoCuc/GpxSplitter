/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gpxsplitter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Antonio
 */
public class GpxFileFilter extends FileFilter
{

    public boolean accept(File pathname)
    {
        if (pathname.isDirectory() || pathname.getName().endsWith(GpxFileBuilder.GPX_FORMAT))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getDescription()
    {
        return "Gpx files (*.gpx)";
    }
}

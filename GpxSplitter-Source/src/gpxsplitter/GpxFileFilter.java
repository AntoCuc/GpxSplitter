/**
 * File filter that only allows .gpx files and directories.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter;

import gpxsplitter.tools.builders.GpxFileBuilder;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class GpxFileFilter extends FileFilter
{


    @Override
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

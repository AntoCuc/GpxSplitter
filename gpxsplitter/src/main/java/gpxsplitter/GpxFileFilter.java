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
    static final String GPX_FILE_DESCRIPTION = "Gpx files (*.gpx)";

    @Override
    public boolean accept(File pathname)
    {
        return pathname.isDirectory() || pathname.getName().endsWith(GpxFileBuilder.GPX_EXTENSION);
    }

    @Override
    public String getDescription()
    {
        return GPX_FILE_DESCRIPTION;
    }
}

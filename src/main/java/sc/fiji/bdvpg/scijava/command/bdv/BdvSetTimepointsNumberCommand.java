package sc.fiji.bdvpg.scijava.command.bdv;

import bdv.util.BdvHandle;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import sc.fiji.bdvpg.scijava.ScijavaBdvDefaults;

@Plugin(type = Command.class, menuPath = ScijavaBdvDefaults.RootMenu+"Bdv>Set Number Of Timepoints",
    label = "Sets the number of timepoints in one or several Bdv Windows")
public class BdvSetTimepointsNumberCommand implements Command {
    @Parameter(label = "Select Bdv Windows")
    BdvHandle[] bdvhs;

    @Parameter(label = "Number of timepoints, min = 1", min = "1")
    int numberOfTimePoints;

    public void run() {
        for (BdvHandle bdvh : bdvhs) {
            bdvh.getViewerPanel().setNumTimepoints(numberOfTimePoints);
        }
    }

}
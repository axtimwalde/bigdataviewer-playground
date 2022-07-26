package sc.fiji.bdvpg.scijava.command.bdv;

import bdv.util.BdvHandle;
import ij.IJ;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import sc.fiji.bdvpg.bdv.BdvHandleHelper;
import sc.fiji.bdvpg.scijava.ScijavaBdvDefaults;
import sc.fiji.bdvpg.scijava.command.BdvPlaygroundActionCommand;

@Plugin(type = BdvPlaygroundActionCommand.class, menuPath = ScijavaBdvDefaults.RootMenu+"BDV>BDV - Add center cross",
        description = "Adds a centering cross onto BDV windows")
public class BdvCrossAdderCommand implements BdvPlaygroundActionCommand{

    @Parameter(label = "Select BDV Windows")
    BdvHandle[] bdvhs;

    @Override
    public void run() {
        if (bdvhs.length==0) IJ.log("Please make sure to select a Bdv window.");
        for (BdvHandle bdvh:bdvhs) {
            BdvHandleHelper.addCenterCross(bdvh);
        }
    }
}

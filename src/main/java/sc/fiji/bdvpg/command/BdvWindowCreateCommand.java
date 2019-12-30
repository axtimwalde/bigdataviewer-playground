package sc.fiji.bdvpg.command;

import bdv.util.*;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.object.ObjectService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import sc.fiji.bdvpg.bdv.BdvCreator;
import sc.fiji.bdvpg.scijava.GuavaWeakCacheService;
import sc.fiji.bdvpg.scijava.ScijavaBdvDefaults;
import sc.fiji.bdvpg.scijava.bdv.BdvHandleHelper;

@Plugin(type = Command.class, menuPath = ScijavaBdvDefaults.RootMenu+"Create Empty BDV Frame",
    label = "Creates an empty Bdv window")
public class BdvWindowCreateCommand implements Command {

    @Parameter(label = "Create a 2D Bdv window")
    public boolean is2D = false;

    @Parameter(label = "Title of the new Bdv window")
    public String windowTitle = "Bdv";

    @Parameter(type = ItemIO.OUTPUT)
    public BdvHandle bdvh;

    @Parameter(label = "Location of the view of the new Bdv window")
    public double px = 0, py = 0, pz = 0;

    @Parameter(label = "Field of view size of the new Bdv window")
    public double s = 100;

    @Parameter
    GuavaWeakCacheService cacheService;

    @Parameter
    ObjectService os;


    @Override
    public void run() {
        //------------ BdvHandleFrame
        BdvOptions bdvOptions = new BdvOptions().frameTitle( windowTitle );
        if ( is2D ) bdvOptions =  bdvOptions.is2D();
        BdvCreator creator = new BdvCreator( bdvOptions );
        creator.run();
        bdvh = creator.get();
        //------------ Allows to remove the BdvHandle from the objectService when closed by the user
        BdvHandleHelper.setBdvHandleCloseOperation(bdvh,os,cacheService, true);
        //------------ Renames window to ensure unicity
        windowTitle = BdvHandleHelper.getUniqueWindowTitle(os, windowTitle);
        BdvHandleHelper.setWindowTitle(bdvh, windowTitle);
    }
}

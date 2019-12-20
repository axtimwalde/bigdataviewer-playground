package sc.fiji.bdvpg.command;

import bdv.util.BdvHandle;
import bdv.viewer.Source;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import sc.fiji.bdvpg.bdv.navigate.ViewerTransformAdjuster;
import sc.fiji.bdvpg.bdv.source.append.SourceBdvAdder;
import sc.fiji.bdvpg.scijava.ScijavaBdvDefaults;
import sc.fiji.bdvpg.bdv.source.display.BrightnessAutoAdjuster;
import sc.fiji.bdvpg.source.importer.SourceLoader;

import java.io.File;

@Plugin( type = Command.class, menuPath = ScijavaBdvDefaults.RootMenu+"Tools>Add Single XML/HDF5" )
public class SingleSourceLoaderCommand implements Command
{
	@Parameter(type = ItemIO.BOTH)
	public static BdvHandle bdvHandle;

	@Parameter ( label = "XML/HDF5 Image Source File")
	private File file;

	@Parameter ( label = "Automatically Adjust Brightness")
	private boolean autoAdjustBrightness;

	@Parameter ( label = "Automatically Adjust Viewer Transform")
	private boolean autoAdjustViewerTransform;

	private Source source;

	@Override
	public void run()
	{
		loadSource();
		addSource();
		if ( autoAdjustBrightness ) adjustBrightness();
		if ( autoAdjustViewerTransform ) adjustViewerTransform();
	}

	public void adjustViewerTransform()
	{
		new ViewerTransformAdjuster( bdvHandle, source ).run();
	}

	public void adjustBrightness()
	{
		new BrightnessAutoAdjuster( bdvHandle, source, 0.01, 0.99 ).run();
	}

	public void addSource()
	{
		new SourceBdvAdder( bdvHandle, source ).run();
	}

	public void loadSource()
	{
		final SourceLoader sourceLoader = new SourceLoader( file.getAbsolutePath() );
		sourceLoader.run();
		source = sourceLoader.getSource( 0 );
	}
}
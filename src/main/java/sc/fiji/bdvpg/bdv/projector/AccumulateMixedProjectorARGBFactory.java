package sc.fiji.bdvpg.bdv.projector;

import bdv.viewer.SourceAndConverter;
import bdv.viewer.render.AccumulateProjectorFactory;
import bdv.viewer.render.VolatileProjector;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.ARGBType;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * For information about this projector, see {@link AccumulateMixedProjectorARGB}
 */

public class AccumulateMixedProjectorARGBFactory implements AccumulateProjectorFactory< ARGBType >
{

	public AccumulateMixedProjectorARGBFactory()
	{
	}

	public VolatileProjector createProjector(
			List< VolatileProjector > sourceProjectors,
			List<SourceAndConverter< ? >> sources,
			List< ? extends RandomAccessible< ? extends ARGBType > > sourceScreenImages,
			RandomAccessibleInterval< ARGBType > targetScreenImage,
			int numThreads,
			ExecutorService executorService )
	{
		return new AccumulateMixedProjectorARGB(
						sourceProjectors,
						sources,
						sourceScreenImages,
						targetScreenImage,
						numThreads,
						executorService );
	}
}

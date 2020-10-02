package sc.fiji.bdvpg.bdv.sourceandconverter;

import bdv.util.BdvHandle;
import bdv.viewer.SourceAndConverter;
import sc.fiji.bdvpg.services.SourceAndConverterServices;

import java.util.function.Consumer;

/**
 * BigDataViewer Playground Action -->
 * Appends a {@link SourceAndConverter} into a {@link BdvHandle}
 *
 * Note :
 * - if a SourceAndConverter is already present, it is not duplicated, the addition
 * is ignored silently
 *
 * - the functional interface allows to use this action in a functional way,
 * in this case, the constructor without SourceAndConverter can be used
 *
 * TODO : think if this action is useful ? It looks ununsed because the direct call to SourceAndConverterServices.getSourceAndConverterDisplayService().show is more convenient
 *
 */

public class SourceAdder implements Runnable, Consumer<SourceAndConverter[]>
{
	SourceAndConverter[] sacsIn;
	BdvHandle bdvh;

	public SourceAdder(BdvHandle bdvh, SourceAndConverter... sacsIn) {
		this.sacsIn=sacsIn;
		this.bdvh=bdvh;
	}

	public SourceAdder(BdvHandle bdvh) {
		this.bdvh=bdvh;
	}

	public void run() {
		accept(sacsIn);
	}

	@Override
	public void accept(SourceAndConverter... sacs) {
		SourceAndConverterServices.getSourceAndConverterDisplayService().show(bdvh, sacs);
	}
}

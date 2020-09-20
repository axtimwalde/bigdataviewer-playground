package sc.fiji.bdvpg.scijava.converters;

import bdv.viewer.SourceAndConverter;
import org.scijava.convert.AbstractConverter;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import sc.fiji.bdvpg.scijava.services.SourceAndConverterService;
import sc.fiji.bdvpg.sourceandconverter.SourceAndConverterUtils;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO : allows multiple Paths splitted by a character ? Not sure it's fool proof
 * @param <I>
 */

@Plugin(type = org.scijava.convert.Converter.class)
public class StringToSourceAndConverterArray<I extends String> extends AbstractConverter<I, SourceAndConverter[]> {

    @Parameter
    SourceAndConverterService sacsService;

    @Override
    public <T> T convert(Object src, Class<T> dest) {
        String str = (String) src;
        TreePath tp = sacsService.getUI().getTreePathFromString(str);
        if (tp!=null) {
            return (T) sacsService.getUI().getSourceAndConvertersFromTreePath(tp).toArray(new SourceAndConverter[0]);//sacsService.getUI().getSourceAndConvertersFromTreePath(tp).toArray(new SourceAndConverter[0]);
        } else {
            return null;
        }
    }

    @Override
    public Class getOutputType() {
        return SourceAndConverter[].class;
    }

    @Override
    public Class<I> getInputType() {
        return (Class<I>) String.class;
    }
}
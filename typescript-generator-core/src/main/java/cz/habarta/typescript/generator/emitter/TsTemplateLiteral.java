
package cz.habarta.typescript.generator.emitter;

import cz.habarta.typescript.generator.Settings;
import java.util.Iterator;
import java.util.List;


public class TsTemplateLiteral extends TsExpression {

    private final List<TsExpression/*|TsStringLiteral*/> spans;

    public TsTemplateLiteral(List<TsExpression> spans) {
        this.spans = spans;
    }

    public List<TsExpression> getSpans() {
        return spans;
    }

    @Override
    public String format(Settings settings) {
        final StringBuilder sb = new StringBuilder();
        sb.append("'");
        for (Iterator<TsExpression> iterator = spans.iterator(); iterator.hasNext();)
		{
			final TsExpression span = iterator.next();
            if (span instanceof TsStringLiteral) {
                final TsStringLiteral literal = (TsStringLiteral) span;
                sb.append(literal.getLiteral());
                if (!iterator.hasNext())
    			{
                	sb.append("'");	
    			}
            } else {
                sb.append("' + ");
                sb.append(span.format(settings));
            }
		}
        return sb.toString();
    }

}

package vaadin.highcharts;

import com.vaadin.annotations.JavaScript;
import org.vaadin.highcharts.AbstractHighChart;

@JavaScript({"jquery.js", "highcharts.js", "exporting.js",
    "highcharts-more.js", "highcharts-3d", "piegradinetcolor.js"})
public class HighChart extends AbstractHighChart {

    private static final long serialVersionUID = -7975150479180453L;
}

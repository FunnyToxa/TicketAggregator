package RESTService.DTO.Entries;

import java.util.List;
import java.util.Map;

public class ReportEntry extends ResponseEntry {
    private List<Map<String, String>> report;

    public List<Map<String, String>> getReport() {
        return report;
    }

    public ReportEntry(List<Map<String, String>> report) {
        this.report = report;
    }
}

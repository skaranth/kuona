package kuona.controller;

import kuona.metric.BuildTriggers;
import kuona.metric.ByDuration;
import kuona.model.BuildResult;
import kuona.model.BuildWithDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMetrics {
    public Map<String, Object> dashboard = new HashMap<>();
    public List<HashMap<String, Object>> dashboardServers;
    public Map<Integer, int[]> activity = new HashMap<>();
    public Map<BuildResult, Integer> buildCountsByResult;
    public ByDuration byDuration = new ByDuration();
    public BuildTriggers triggers = new BuildTriggers();
    public ArrayList<BuildWithDetails> completedBuilds;

    public BuildMetrics() {
        completedBuilds = new ArrayList<>();
        dashboard = new HashMap<>();
        dashboardServers = new ArrayList<>();
        activity = new HashMap<>();

        buildCountsByResult = new HashMap<>();
        for (BuildResult br : BuildResult.values()) {
            buildCountsByResult.put(br, 0);
        }

        byDuration = new ByDuration();
        triggers = new BuildTriggers();
    }

}

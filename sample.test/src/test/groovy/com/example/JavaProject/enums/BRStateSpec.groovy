package com.example.JavaProject.enums
import spock.lang.*
import java.util.stream.Collectors

class BRStateSpec extends Specification {
    @Shared Map<BRRegion, List<BRState>> brStatesByRegion = BRState.stream()
                .collect(Collectors.groupingBy(BRState::getRegion))

    def "Brazil should have 27 states"() {
        expect:
            BRState.values().length == 27
    }

    def "Brazil should have 5 regions"() {
        expect:
            brStatesByRegion.size() == 5
    }

    def "Brazil should contains the correct number of states by region"() {
        expect:
            brStatesByRegion.get(BRRegion.N).size() == 7
            brStatesByRegion.get(BRRegion.NE).size() == 9
            brStatesByRegion.get(BRRegion.SE).size() == 4
            brStatesByRegion.get(BRRegion.S).size() == 3
            brStatesByRegion.get(BRRegion.CO).size() == 4
    }
}

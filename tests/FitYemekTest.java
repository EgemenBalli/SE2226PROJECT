import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FitYemekTest {
    static FitYemek fitYemek = new FitYemek();

    @BeforeAll
    static void init(){
        fitYemek.connect();
    }

    @ParameterizedTest
    @CsvSource({
            "'',  'Bu alani doldurmalisiniz.'"
    })
    @CsvFileSource(resources = "/yas_EquivalencePartitioning.csv")
    void yasInputEquivalencePartitioningTest(String input, String expectedOutput) {
        assertEquals(expectedOutput, fitYemek.yasInput(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'',  'Bu alani doldurmalisiniz.'"
    })
    @CsvFileSource(resources = "/boy_EquivalencePartitioning.csv")
    void boyInputEquivalencePartitioningTest(String input, String expectedOutput) {
        assertEquals(expectedOutput, fitYemek.boyInput(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'',  'Bu alani doldurmalisiniz.'"
    })
    @CsvFileSource(resources = "/kilo_EquivalencePartitioning.csv")
    void kiloInputEquivalencePartitioningTest(String input, String expectedOutput) {
        assertEquals(expectedOutput, fitYemek.kiloInput(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/yas_BoundaryValueAnalysis.csv")
    void yasInputBoundaryValueAnalysis(String input, String expectedOutput){
        assertEquals(expectedOutput, fitYemek.yasInput(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/boy_BoundaryValueAnalysis.csv")
    void boyInputBoundaryValueAnalysis(String input, String expectedOutput){
        assertEquals(expectedOutput, fitYemek.boyInput(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/kilo_BoundaryValueAnalysis.csv")
    void kiloInputBoundaryValueAnalysis(String input, String expectedOutput){
        assertEquals(expectedOutput, fitYemek.kiloInput(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calculateKalori_DesicionTable.csv")
    void calculateKaloriDesicionTableTest(String cinsiyet, String yas, String boy, String kilo, String hareketSeviyesi, String hedef, String expectedOutput){
        assertEquals(expectedOutput, fitYemek.calculateKalori(cinsiyet, yas, boy, kilo, hareketSeviyesi, hedef));
    }
}
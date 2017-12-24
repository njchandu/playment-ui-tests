package util;

import dao.Config;
import dao.TestData;
import org.yaml.snakeyaml.Yaml;

/**
 * Created by chandanjavaregowda on 03/04/17.
 */
public class YamlReader {
    private String filePath;

    public YamlReader(String filePath) {
        this.filePath = filePath;
    }

    public Config readConfig() {
        return new Yaml().loadAs(FileReader.read(filePath), Config.class);
    }

    public TestData readTestData() {
        return new Yaml().loadAs(FileReader.read(filePath), TestData.class);
    }
}

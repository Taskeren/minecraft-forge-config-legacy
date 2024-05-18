import net.minecraftforge.common.config.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestMcForgeConfig {

	private static File getFileInTestResource(String name) {
		return new File("src/test/resources", name);
	}

	@Test
	public void testGeneral() {
		Configuration config = new Configuration(getFileInTestResource("1.cfg"), "v1");

		config.getString("heroName", Configuration.CATEGORY_GENERAL, "Taskeren", "The hero name!");
		config.getInt("heroAge", Configuration.CATEGORY_GENERAL, 16, 0, 100, "The hero age!");
		config.getBoolean("heroAlive", Configuration.CATEGORY_GENERAL, true, "Is the hero alive?");
		config.getFloat("heroAttackDamage", Configuration.CATEGORY_GENERAL, 10.0F, 0.0F, Float.MAX_VALUE, "The attack damage of the hero!");
		config.getStringList("heroAlias", Configuration.CATEGORY_GENERAL, new String[]{"Anti \"DreamMaster\" Master", "The Genius Warlock"}, "The alias of the hero!");

		config.save();
	}

}

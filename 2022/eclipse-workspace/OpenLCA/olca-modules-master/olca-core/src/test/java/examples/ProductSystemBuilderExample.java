package examples;

import java.io.File;

import org.openlca.core.database.IDatabase;
import org.openlca.core.database.ProcessDao;
import org.openlca.core.database.ProductSystemDao;
import org.openlca.core.database.Derby;
import org.openlca.core.matrix.ProductSystemBuilder;
import org.openlca.core.matrix.cache.MatrixCache;
import org.openlca.core.matrix.linking.LinkingConfig;
import org.openlca.core.matrix.linking.ProviderLinking;
import org.openlca.core.model.Process;
import org.openlca.core.model.ProcessType;

/**
 * An example for creating a product system with auto-completion.
 */

/* Application 닫고 실행해야 함 */

public class ProductSystemBuilderExample {

	public static void main(String[] args) {
		String workspace = "C:/Users/KETI/openLCA-data-1.4";
		String dbPath = workspace + "/databases/test1012_copy";
		//String dbPath = "C:/Users/Besitzer/openLCA-data-1.4/databases/ecoinvent_2_2_unit";
		IDatabase db = new Derby(new File(dbPath));

		// load the reference process of the new product system
		Process p = new ProcessDao(db).getForRefId(
				"26c22773-3780-4dc2-9e04-a70dd72b2d68");
		System.out.println("p:" + p);
		
		// create and auto-complete the product system
		var config = new LinkingConfig()
			.providerLinking(ProviderLinking.PREFER_DEFAULTS)
			.preferredType(ProcessType.UNIT_PROCESS);
		var system = new ProductSystemBuilder(MatrixCache.createLazy(db), config)
			.build(p);
		
		system.targetAmount = 1.0;
		system.description = "test1021";

		// save the product system
		new ProductSystemDao(db).insert(system);
		
	}
}

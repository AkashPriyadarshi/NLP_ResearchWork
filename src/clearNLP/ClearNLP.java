package clearNLP;

import com.clearnlp.component.AbstractComponent;
import com.clearnlp.dependency.DEPTree;
import com.clearnlp.nlp.NLPGetter;
import com.clearnlp.nlp.NLPMode;
import com.clearnlp.reader.AbstractReader;
import com.clearnlp.run.Version;
import com.clearnlp.tokenization.AbstractTokenizer;
public class ClearNLP {

	final String language = AbstractReader.LANG_EN;
	private String sentence = new String("This is a test input");

	private void dependencyTreeParser(String modelType) {
		try {
			AbstractTokenizer tokenizer = NLPGetter.getTokenizer(language);
			AbstractComponent tagger     = NLPGetter.getComponent(modelType, language, NLPMode.MODE_POS);
			AbstractComponent parser = NLPGetter.getComponent(modelType, language, NLPMode.MODE_DEP);
			AbstractComponent labeler    = NLPGetter.getComponent(modelType, language, NLPMode.MODE_SRL);
			DEPTree tree = NLPGetter.toDEPTree(tokenizer.getTokens(sentence));
			tagger.process(tree);
			parser.process(tree);
			labeler.process(tree);
			System.out.println(tree.toStringSRL()+"\n");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main (String args[]) {
		Version.main(args);
		new ClearNLP().dependencyTreeParser("general-en");
	}

}


package card;

import java.util.Map;

import card.model.CardBean;
import card.service.CardService;
import card.service.impl.CardServiceImpl;

public class TestCard {

	public static void main(String[] args) {
		int pageNo =1;
		
		
		CardService service = new CardServiceImpl();
		Map<Integer, CardBean> CardMap = service.getCbPageCards(pageNo);
		
	
		
		int cardCounts = CardMap.size();
		System.out.println(cardCounts);
		
	}

}

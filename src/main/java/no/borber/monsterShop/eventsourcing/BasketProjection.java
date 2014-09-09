package no.borber.monsterShop.eventsourcing;

import java.util.HashMap;
import java.util.Map;

import no.borber.monsterShop.basket.Basket;
import no.borber.monsterShop.basket.BasketItem;
import no.borber.monsterShop.monsterTypes.MonsterTypeJson;

public class BasketProjection {

	public Map<String, Basket> baskets = new HashMap<>();

	public BasketProjection(EventStore es) {
		es.subscribe(this);
	}

	public void notifyEvent(Event event) {
		if (event.aggregateType == Basket.class) {
			Basket basket = baskets.get(event.aggregateId);

			if (basket == null) {
				basket = new Basket();
			}

			if (event instanceof AddMonsterToBasketEvent) {
				MonsterTypeJson monsterType = ((AddMonsterToBasketEvent) event).monsterType;
				BasketItem bi = new BasketItem(monsterType.getName(),
						monsterType.getPrice());
				basket.items.add(bi);

				System.out.println("Basket Item added");

				// FIXME if monster exists, only increase the count
				baskets.put(event.aggregateId, basket);
			}
			// FIXME other events (remove monster)
		}
	}

}

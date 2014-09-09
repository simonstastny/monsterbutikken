package no.borber.monsterShop.eventsourcing;

import java.util.List;

import no.borber.monsterShop.basket.BasketAggregate;
import no.borber.monsterShop.monsterTypes.MonsterTypeJson;
import no.borber.monsterShop.monsterTypes.MonsterTypesRepo;

public class BasketService {

	private final EventStore es;

	public BasketService(EventStore es) {
		this.es = es;
	}

	public void addMonsterToBasket(String mt, String basketId) {
		// loading basket
		List<Event> events = es.getEventsFor(BasketAggregate.class, basketId);
		BasketAggregate basket = new BasketAggregate(events, basketId);

		// adding monster to basket
		MonsterTypeJson monsterType = MonsterTypesRepo.getMonsterType(mt);
		es.storeEvent(basket.addMonsterToBasketEvent(monsterType));
	}

	/*
	 * public void removeMonsterFromBasket(String mt, String basketId) { //
	 * loading basket List<Event> events =
	 * es.getEventsFor(BasketAggregate.class, basketId); BasketAggregate basket
	 * = new BasketAggregate(events);
	 * 
	 * // // removing a monster from the basket // MonsterTypeJson monsterType =
	 * MonsterTypesRepo.getMonsterType(mt); //
	 * es.storeEvent(basket.removeMonsterFromBasket(monsterType)); }
	 */

}

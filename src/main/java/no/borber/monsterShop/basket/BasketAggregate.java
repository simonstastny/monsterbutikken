package no.borber.monsterShop.basket;

import java.util.List;

import no.borber.monsterShop.eventsourcing.AddMonsterToBasketEvent;
import no.borber.monsterShop.eventsourcing.Event;
import no.borber.monsterShop.monsterTypes.MonsterTypeJson;

public class BasketAggregate {

	public String aggregateId;

	public BasketAggregate(List<Event> events, String aggregateId) {
		this.aggregateId = aggregateId;
		for (Event event : events) {
			processEvent(event);
		}
	}

	public void processEvent(Event event) {
		// ignoring events..
	}

	public AddMonsterToBasketEvent addMonsterToBasketEvent(
			MonsterTypeJson monsterType) {
		return new AddMonsterToBasketEvent(monsterType, aggregateId);
	}

}

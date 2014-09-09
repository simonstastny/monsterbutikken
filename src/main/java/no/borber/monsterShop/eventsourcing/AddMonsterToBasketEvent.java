package no.borber.monsterShop.eventsourcing;

import no.borber.monsterShop.basket.Basket;
import no.borber.monsterShop.monsterTypes.MonsterTypeJson;

public class AddMonsterToBasketEvent extends Event {

	MonsterTypeJson monsterType;

	public AddMonsterToBasketEvent(MonsterTypeJson monsterName,
			String aggregateId) {
		super();
		this.monsterType = monsterName;
		this.aggregateId = aggregateId;
		this.aggregateType = Basket.class;
	}

}

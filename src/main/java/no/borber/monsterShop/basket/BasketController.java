package no.borber.monsterShop.basket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import no.borber.monsterShop.MonsterShopController;
import no.borber.monsterShop.eventsourcing.BasketProjection;
import no.borber.monsterShop.eventsourcing.BasketService;
import no.borber.monsterShop.eventsourcing.EventStore;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class BasketController extends MonsterShopController {

	EventStore es = new EventStore();
	BasketService bs = new BasketService(es);
	BasketProjection bp = new BasketProjection(es);

	/**
	 * Gets the current state of a customers basket
	 *
	 * @return Map of String monsterType og basketItem for the applicable
	 *         monster type.
	 */
	@RequestMapping(value = "/basket/", method = RequestMethod.GET)
	@ResponseBody()
	public Map<String, BasketItem> getBasket() {
		if (httpRequest.getSession().getAttribute("basketId") == null) {
			httpRequest.getSession().setAttribute("basketId",
					UUID.randomUUID().toString());
		}

		String basketId = (String) httpRequest.getSession().getAttribute(
				"basketId");

		Map<String, BasketItem> mp = new HashMap<String, BasketItem>();

		if (bp.baskets.get(basketId) != null) {
			for (BasketItem bi : bp.baskets.get(basketId).items) {
				mp.put(bi.getName(), bi);
			}
		}
		return mp;
	}

	/**
	 * Adds a new monster of a specified type to the customers basket. If there
	 * is an existing basket item the number of monsters is incremented,
	 * otherwise a new order baslet item is created.
	 *
	 * @param monstertype
	 *            name of the monstertype to be added
	 */
	@RequestMapping(value = "/basket/{monstertype}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void add(@PathVariable String monstertype) {
		if (httpRequest.getSession().getAttribute("basketId") == null) {
			httpRequest.getSession().setAttribute("basketId",
					UUID.randomUUID().toString());
		}

		String basketId = (String) httpRequest.getSession().getAttribute(
				"basketId");

		bs.addMonsterToBasket(monstertype, basketId);
	}

	/**
	 * Removes a monster from the customers basket. If the resulting number of
	 * monsters reaches 0, the basket item is removed.
	 *
	 * @param monstertype
	 *            name of the monstertype to be removed
	 */
	@RequestMapping(value = "/basket/{monstertype}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable String monstertype) {

	}

	/**
	 * Calculates the sum of (price * number) for all items in the basket.
	 */
	@RequestMapping(value = "/basket/sum", method = RequestMethod.GET)
	@ResponseBody
	public BasketSum sum() {
		return null;
	}

}

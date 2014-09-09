package no.borber.monsterShop.eventsourcing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventStore {

	private final List<Event> events = new ArrayList<>();
	private final List<BasketProjection> subscribers = new ArrayList<>();

	public EventStore() {
		
	}

	public List<Event> getEventsFor(Class aggregateType, String aggregateId) {
		return events.stream()
				.filter(event -> event.aggregateId.equals(aggregateId))
				.filter(event -> event.aggregateType.equals(aggregateType))
				.collect(Collectors.toList());
	}

	/*
	 * The event store should, after a new event is received and stored, publish
	 * the event to any subscribing projections.
	 */
	public void storeEvent(Event event) {
		this.events.add(event);

		for (BasketProjection subscriber : subscribers) {
			subscriber.notifyEvent(event);
		}
	}

	/**
	 * The projection should be able to subscribe to events from the event
	 * store. On recieving a subscription, the event store should send all
	 * stored events to the subscribing projection.
	 * 
	 * @param subscriber
	 */
	public void subscribe(BasketProjection subscriber) {
		this.subscribers.add(subscriber);

		for (Event event : events) {
			subscriber.notifyEvent(event);
		}
	}

}

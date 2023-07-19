package hello.domain.item;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;

class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository();

	@AfterEach
	void afterEach() {
		itemRepository.clearStore();
	}

	@Test
	void save() {
		//given
		Item item = new Item("itemA", 10000,10);

		//when
		Item saveItem = itemRepository.save(item);

		//then
		Item findItem = itemRepository.findById(item.getId());
		assertThat(saveItem).isEqualTo(findItem);
	}

	@Test
	void findAll() {
		Item item1 = new Item("itemA", 10000,10);
		Item item2 = new Item("itemA", 10000,10);
		//when
		itemRepository.save(item1);
		itemRepository.save(item2);

		//when
		List<Item> result = itemRepository.findAll();

		assertThat(result.size()).isEqualTo(2);
		assertThat(result).contains(item1, item2);
	}

	@Test
	void update() {
		Item item1 = new Item("item1", 10000,10);
		Item saveItem = itemRepository.save(item1);
		Long itemId = saveItem.getId();

		Item item2 = new Item("item2", 20000,15);

		itemRepository.update(itemId, item2);

		Item findItem = itemRepository.findById(itemId);

		assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());
	}
}
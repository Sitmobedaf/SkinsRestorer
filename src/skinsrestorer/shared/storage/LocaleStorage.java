package skinsrestorer.shared.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import skinsrestorer.libs.com.google.gson.Gson;
import skinsrestorer.libs.com.google.gson.GsonBuilder;

public class LocaleStorage {

	private static final String localefile = "locale.json";
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private static final LocaleStorage instance = new LocaleStorage();

	public static final LocaleStorage getInstance() {
		return instance;
	}

	public static void init(File datafolder) {
		File fullpath = new File(datafolder, localefile);
		try (FileReader reader = new FileReader(fullpath)) {
			LocaleStorage other = gson.fromJson(reader, LocaleStorage.class);
			for (Field field : LocaleStorage.class.getFields()) {
				Object value = field.get(other);
				if (value != null) {
					field.set(instance, value);
				}
			}
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
		}
		try (FileWriter writer = new FileWriter(fullpath)) {
			datafolder.mkdirs();
			writer.write(gson.toJson(instance));
		} catch (IOException e) {
		}
	}

	public String PLAYER_SKIN_CHANGE_SKIN_DATA_CLEARED = "Ваш скин был аннулирован.";
	public String PLAYER_SKIN_CHANGE_COOLDOWN = "Вы можете менять скин не более одного раза в 30 секунд.";
	public String PLAYER_SKIN_CHANGE_SUCCESS = "Ваш скин успешно изменён. Перезайдите на сервер, чтобы изменения вступили в силу.";
	public String PLAYER_SKIN_CHANGE_FAILED = "Произошла ошибка при получении данных: ";
	public String INVALID_COMMAND_ARGUMENTS = "§eИспользуйте - /skin <ник>";

	public String SKIN_FETCH_FAILED_NO_PREMIUM_PLAYER = "Данный ник не имеет скина.";
	public String SKIN_FETCH_FAILED_NO_SKIN_DATA = "Не удалось получить данные скина.";
	public String SKIN_FETCH_FAILED_PARSE_FAILED = "Не удалось преобразовать данные скина.";
	public String SKIN_FETCH_FAILED_RATE_LIMITED = "Превышен лимит";
	public String SKIN_FETCH_FAILED_ERROR = "Произошла ошибка";

}

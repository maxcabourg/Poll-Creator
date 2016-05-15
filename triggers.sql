DELIMITER //
CREATE TRIGGER filter BEFORE INSERT ON Poll
 FOR EACH ROW BEGIN
	IF NEW.question LIKE '%fuck%' OR NEW.question LIKE '%shit%' THEN
		signal sqlstate '45000';
	END IF;
END
//

DELIMITER //
CREATE TRIGGER on_delete_poll BEFORE DELETE ON Poll
 FOR EACH ROW INSERT INTO deleted_poll_history (id_poll_deleted, content_poll_deleted) VALUES (OLD.id_poll, OLD.question)
//
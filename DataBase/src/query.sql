SELECT U.USER_ID, U.USERNAME, TD.TRAINING_ID, TD.TRAINING_DATE, count(*) as count
FROM "User" U
JOIN PUBLIC.TRAINING_DETAILS TD
ON U.USER_ID = TD.USER_ID
GROUP BY U.USER_ID, U.USERNAME, TD.TRAINING_ID, TD.TRAINING_DATE
HAVING count > 1
ORDER BY TD.TRAINING_DATE DESC
SELECT
M.MissionID,
M.MissionName,
M.RewardPoints,
M.Status,
M.Region
FROM
Mission AS M
JOIN
User AS U ON M.UserID = U.UserID
WHERE
U.UserID = 1  -- 현재 로그인된 사용자의 ID
AND M.Region = '안암동'  -- 사용자가 선택한 지역 (예: '안암동')
ORDER BY
M.MissionID DESC  -- 최신 미션을 먼저 표시
LIMIT 10 OFFSET 0;  -- 페이징: 한 페이지당 10개의 미션, 첫 페이지
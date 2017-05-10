
-- insert 3 sample rows
insert into registry_server_node (server_node_id, web_name, url, type, last_updated, version)
  values(1, 'GA4GH beacon', 'http://beacon.ga4gh.org', 'beacon', sysdate(), 0);

insert into registry_server_node (server_node_id, web_name, url, type, last_updated, version)
  values(2, 'GA4GH Matchmaker', 'http://matchmaker.ga4gh.org', 'matchmaker', sysdate(), 0);

insert into registry_server_node (server_node_id, web_name, url, type, last_updated, version)
  values(3, 'Brroad Institute beacon', 'http://beacon.broadinstitute.org', 'beacon', sysdate(), 0);


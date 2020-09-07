import urllib.request as r
import json

url = r.urlopen(
    "https://raw.githubusercontent.com/theikkila/postinumerot/master/postcode_map_light.json")
html = url.read()
postitoimipaikat = json.loads(html)

ptp = input("Kirjoita postitoimipaikka: ").strip().upper()
print("")

postinumerot = []
tulostus = "Postinumerot:"

for key in postitoimipaikat:
    if postitoimipaikat[key] == ptp:
        tulostus += str(" " + key + ",")

print(tulostus[:-1])

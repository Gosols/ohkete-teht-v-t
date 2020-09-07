import urllib.request as r
import json

url = r.urlopen(
    "https://raw.githubusercontent.com/theikkila/postinumerot/master/postcode_map_light.json")
html = url.read()
postitoimipaikat = json.loads(html)

postinumero = input("Kirjoita postinumero: ")
print("")

for key in postitoimipaikat:
    if key == postinumero:
        print(postitoimipaikat[key])

import { readFileSync, writeFileSync } from "node:fs";
import { resolve, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const pkgPath = resolve(__dirname, "../package.json");
const pkg = JSON.parse(readFileSync(pkgPath, "utf-8"));

const mode = process.argv[2];
if (mode !== "local" && mode !== "registry") {
    console.error("Usage: node scripts/toggle-deps.mjs <local|registry>");
    process.exit(1);
}

const modules = ["common", "players", "clans", "matches", "leaderboards", "mastery", "stats"];
const version = pkg.version === "1.0.0" ? "1.0.1" : pkg.version;

for (const mod of modules) {
    const key = `@pubgkt/${mod}`;
    if (mode === "local") {
        pkg.dependencies[key] = `file:../../core/build/npmPackages/${mod}/pubgkt-${mod}-${version}.tgz`;
    } else {
        pkg.dependencies[key] = version;
    }
}

writeFileSync(pkgPath, JSON.stringify(pkg, null, 2) + "\n");
console.log(`Switched @pubgkt/* dependencies to ${mode} (version ${version})`);

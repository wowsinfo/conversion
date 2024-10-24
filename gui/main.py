from __future__ import annotations
import flet as ft
import sys
from os import path as os_path

convert_path = os_path.join(os_path.dirname(os_path.dirname(os_path.abspath(__file__))))

if convert_path in sys.path:
    print("convert path already in sys.path")
    raise Exception("convert path already in sys.path")
print(f"Adding {convert_path} to sys.path")
sys.path.append(convert_path)

from convert.engine import EngineConfig


def main(page: ft.Page):
    page.title = "Code Convertion"

    def route_change(route):
        page.views.clear()
        page.views.append(
            ft.View(
                "/",
                [
                    ft.AppBar(
                        title=ft.Text("Flet app"), bgcolor=ft.colors.SURFACE_VARIANT
                    ),
                    ft.ElevatedButton(
                        "Visit Store", on_click=lambda _: page.go("/store")
                    ),
                ],
            )
        )
        if page.route == "/store":
            page.views.append(
                ft.View(
                    "/store",
                    [
                        ft.AppBar(
                            title=ft.Text("Store"), bgcolor=ft.colors.SURFACE_VARIANT
                        ),
                        ft.ElevatedButton("Go Home", on_click=lambda _: page.go("/")),
                    ],
                )
            )
        page.update()

    def view_pop(view):
        page.views.pop()
        top_view = page.views[-1]
        page.go(top_view.route)

    page.on_route_change = route_change
    page.on_view_pop = view_pop
    page.go(page.route)


ft.app(main)

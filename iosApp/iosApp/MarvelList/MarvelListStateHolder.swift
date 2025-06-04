//
//  MarvelListStateHolder.swift
//  iosApp
//
//  Created by Ruben Marquez on 3/6/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

class MarvelListStateHolder: ObservableObject {
    enum UIState {
        case loading
        case success([CharacterEntity])
        case error(String)
    }

    @Published var state: UIState = .loading
    private let wrapper = MarvelListViewModelWrapper()

    init() {
        wrapper.state.subscribe { newState in
            DispatchQueue.main.async {
                switch newState {
                case is MarvelListUiState.Loading:
                    self.state = .loading
                case let success as MarvelListUiState.Success:
                    self.state = .success(success.characters)
                case let error as MarvelListUiState.Error:
                    self.state = .error(error.message)
                default:
                    self.state = .error("Unknown state")
                }
            }
        }
    }

    deinit {
        wrapper.state.unsubscribe()
    }

    func onPullToRefresh() {
        wrapper.onPullToRefresh()
    }

    func onEndReached() {
        wrapper.onEndReached()
    }
}

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

    func start() {
        // Ideally poll or hook into observable state from shared.
        // This is a placeholder pattern until proper Flow bridging is done.
        Timer.scheduledTimer(withTimeInterval: 1.0, repeats: true) { [weak self] _ in
            guard let self else { return }
            let current = self.wrapper.currentState()

            if current.starts(with: "loading") {
                self.state = .loading
            } else if current.starts(with: "success:") {
                // Real shared state would give characters; this is a simulation
                self.state = .success([]) // Replace with actual list when Flow is bridged
            } else if current.starts(with: "error:") {
                self.state = .error(current.replacingOccurrences(of: "error: ", with: ""))
            }
        }
    }

    func onPullToRefresh() {
        wrapper.onPullToRefresh()
    }

    func onEndReached() {
        wrapper.onEndReached()
    }
}
